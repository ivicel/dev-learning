var {body} = document;
var WIDTH = 1024;
var RATIO = 3;

new Vue({
    el: '#app',
    data() {
        return {
            article: {
                title: '',
                titlePic: '',
                category: {
                    id: -1,
                    name: ''
                },
                tags: [],
                author: '',
                content: '',
                contentMd: '',
                origin: window.origin
            },
            category: '',
            categories: [],
            defaultActive: '2',
            dynamicTags: [],
            inputVisible: false,
            token: {name: ''},
            mobileStatus: false,
            sidebarStatus: true,
            sidebarFlag: ' openSidebar ',
        }
    },
    methods: {
        categoryChange(data) {
            this.article.category = data;
        },
        publishBtn(state) {
            this.article.state = state;
            this.save();
        },
        buildPath(path, args) {
            let argStr = "";
            let mark = true;
            if (args !== null && typeof (args) === 'object') {
                for (let key in args) {
                    if (mark) {
                        argStr += "?";
                        mark = false;
                    } else {
                        argStr += "&";
                    }
                    argStr += key + "=" + args[key];
                }
            }

            return encodeURI(BASE_PATH.concat(path, argStr));
        },
        save() {
            this.article.content = window.markdownContent.getHTML();
            this.article.contentMd = window.markdownContent.getMarkdown();
            this.article.tags = this.dynamicTags;
            if (typeof(this.article.category) === "string") {
                this.article.category = {
                    id: -1,
                    name: this.category
                };
            }
            axios.post(this.buildPath('/admin/article/save'), this.article).then(resp => {
                if (resp.status === 200 && resp.data.code === 0) {
                    window.location.reload();
                    this.$message({
                        showClose: true,
                        message: resp.data.data,
                        type: 'success'
                    });
                } else {
                    this.$message({
                        showClose: true,
                        message: resp.data.data || "操作失败",
                        type: 'error'
                    });
                }
            }, resp => {
                this.$message({
                    showClose: true,
                    message: resp.data.data || "操作失败",
                    type: 'error'
                });
            });
        },

        handleCloseTag(tag) {
           this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },

        showInput() {
           this.inputVisible = true;
           this.$nextTick(_ => {
               this.$refs.saveTagInput.$refs.input.focus();
           });
        },

        handleInputConfirm() {
            let inputValue = this.article.tags;
            if (inputValue) {
                this.dynamicTags.push(inputValue);
            }
            this.inputVisible = false;
            this.article.tags = '';
        },

        init() {
            axios.get(this.buildPath('/category/findAll')).then(resp => {
                resp.data.data.forEach(row => {
                    if (row.name != null) {
                        this.categories.push({
                            id: row.id,
                            name: row.name
                        });
                    }
                })
            });

            axios.get(this.buildPath('/admin/info')).then(resp => {
                this.token.name = resp.data.data.name;
            });
        },

        isMobile() {
            const rect = body.getBoundingClientRect();
            return rect.width - RATIO < WIDTH
        },

        handleSidebar() {
            if (this.sidebarStatus) {
                this.sidebarFlag = ' hideSidebar ';
                this.sidebarStatus = false;

            } else {
                this.sidebarFlag = ' openSidebar ';
                this.sidebarStatus = true;
            }
            const isMobile = this.isMobile();
            if (isMobile) {
                this.sidebarFlag += ' mobile ';
                this.mobileStatus = true;
            }
        },
        //蒙版
        drawClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
    created() {
        this.init();

        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    },
});