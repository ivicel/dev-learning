var {body} = document;
var WIDTH = 1024;
var RATIO = 3;

new Vue({
    el: "#main-page-app",
    data() {
        return {
            totalPage: 0,
            pageNum: 1,
            pageSize: 12,
            posts: [{
                title: '',
                summary: '',
                author: '',
                createTime: '',
                titlePic: '',
                content: '',
                category: {
                    id: -1,
                    name: ''
                }
            }]

        }
    },
    methods: {
        onPageChange(val) {
            this.pageNum = val;
            this.fetchPost();
        },
        fetchPost() {
            axios.post(this.buildPath("/article/findAll", {
                pageNum: this.pageNum,
                pageSize: this.pageSize
            })).then(resp => {
                if (resp.data.code === 0) {
                    this.totalPage = resp.data.data.total;
                    this.posts = resp.data.data.rows;
                }
            }).catch(error => {

            })
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
    mounted() {
        this.fetchPost();
    }
});