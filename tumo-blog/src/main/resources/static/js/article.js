var {body} = document;
var WIDTH = 1024;
var RATIO = 3;

new Vue({
    el: '#app',
    data() {
        return {
            pageInfo: {},
            article: [{
                id: '',
                title: '',
                titlePic: '',
                category: {},
                author: '',
                content: '',
                state: '',
                publishTime: '',
                editTime: '',
                createTime: ''
            }],
            pageConf: {
                pageNum: 1,
                pageSize: 10,
                totalPage: 12,
                pageOption: [10, 20, 30, 50]
            },
            defaultActive: '3',
            searchEntity: {},
            token: {name: ''},
            mobileStatus: false,
            sidebarStatus: true,
            sidebarFlag: ' openSidebar '
        }
    },
    methods: {
        reloadList() {
            this.search(this.pageConf.pageNum, this.pageConf.pageSize);
        },
        search(pageNum, pageSize) {
            axios.post(this.buildPath('/article/findAll', {
                'pageNum': pageNum,
                'pageSize': pageSize
            }), this.searchEntity).then(resp => {
                if (resp.data.code === 0) {
                    this.article = resp.data.data.rows;
                    this.pageConf.totalPage = resp.data.data.total;
                }
            });
        },
        handleSizeChange(val) {
            this.search(this.pageConf.pageNum, val);
        },
        handleCurrentChange(val) {
            this.pageConf.pageNum = val;
            this.search(val, this.pageConf.pageSize);
        },
        sureDelete(ids) {
            this.$confirm('确定删除此文章?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                axios.post(this.buildPath("/admin/article/delete"), JSON.stringify(ids).then(resp => {
                    if (resp.data.code === 200) {
                        this.$message({
                            type: 'success',
                            message: resp.data.data,
                            duration: 6000
                        });

                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize ===
                                (this.pageConf.pageNum - 1)) {
                            this.pageConf.pageNum = this.pageConf.pageNum - 1;
                        }
                        this.reloadList();
                    } else {
                        this.$message({
                            type: 'warning',
                            message: resp.data.data,
                            duration: 6000
                        });
                        this.reloadList();
                    }
                }));
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },
        handleDelete(id) {
            this.sureDelete([id]);
        },
        init() {
            this.search(this.pageConf.pageNum, this.pageConf.pageSize);
            axios.get(this.buildPath('/admin/info')).then(resp => {
                this.token.name = resp.data.data.name;
            });
        },
        isMobile() {
            return document.body.getBoundingClientRect() - RATIO < WIDTH;
        },
        handleSidebar() {
            if (this.sidebarStatus) {
                this.sidebarFlag = ' hideSidebar ';
                this.sidebarStatus = false;
            } else {
                this.sidebarFlag = ' openSidebar ';
                this.sidebarStatus = true;
            }
            if (this.isMobile()) {
                this.sidebarFlag += ' mobile ';
                this.mobileStatus = true;
            }
        },
        drawClick() {
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
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
        }
    },
    created() {
        this.init();
        const isMobile = this.isMobile();
        if (isMobile) {
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    }
});