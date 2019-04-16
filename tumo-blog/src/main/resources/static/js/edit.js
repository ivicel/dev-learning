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
                category: '',
                tags: [],
                author: '',
                content: '',
                contentMd: '',
                origin: '',
            },
            categories: [{
                id: '',
                name: '',
            }],

            defaultActive: '3',

            //tags
            dynamicTags: [],
            inputVisible: false,

            //=========select分类选择==========
            options: [{
                value: '',
                label: ''
            }],
            token: {name: ''},

            mobileStatus: false, //是否是移动端
            sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
            sidebarFlag: ' openSidebar ', //侧边栏标志
        }
    },
    methods: {
        //点击存入草稿
        save() {
            this.article.content = window.markdownContent.getHTML(); //给content赋值
            this.article.contentMd = window.markdownContent.getMarkdown(); //给contentMd赋值
            this.article.tags = this.dynamicTags; //给tags字段赋值

            axios.put(this.buildPath('/admin/article/update'), this.article).then(result => {
                if (result.data.code === 0) {
                    this.$message({
                        showClose: true,
                        message: result.data.data,
                        type: 'success'
                    });
                    window.location.href = this.buildPath('/admin/article');
                } else {
                    this.$message({
                        showClose: true,
                        message: result.data.data,
                        type: 'error'
                    });
                }
            });

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
        categoryChange(category) {
            this.article.category = category;
        },

        //点击发布文章
        publishBtn(state) {
            this.article.state = state; //0:存入草稿；1:发布
            this.save();
        },

        //===============标签==================
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

        init(id) {
            //得到所有的分类列表
            axios.get(this.buildPath('/category/findAll')).then(result => {
                if (result.data.code === 0) {
                    this.categories = result.data.data;
                }
            });

            //已登录用户名
            axios.get(this.buildPath('/admin/info')).then(result => {
                this.token.name = result.body.data.name;
            });

            //从url中获取参数查询文章数据
            axios.get(this.buildPath('/admin/article/' + id)).then(result => {
                if (result.data != null) {
                    this.article = result.data;
                    this.dynamicTags = result.data.tags;
                }
            });
        },

        getUrlParam() {
            let hash = '';
            let path = window.location.href;
            if (path.indexOf('?') === -1 && path.indexOf('#') === -1) {
                hash = path.substring(path.lastIndexOf('/') + 1);
            } else {
                if (path.indexOf('?') === -1 || path.indexOf('#') === -1) {
                    if (path.indexOf('?') > path.indexOf('#')) {
                        //说明 ？在 # 前
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('?'));
                    } else {
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('#'));
                    }
                } else {
                    if (path.indexOf('?') > path.indexOf('#')) {
                        //说明 ？在 # 前
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('#'));
                    } else {
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('?'));
                    }
                }
            }
            return hash;
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
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
    mounted() {
        this.init(this.getUrlParam());

        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    },
});

