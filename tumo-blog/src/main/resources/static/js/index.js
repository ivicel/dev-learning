var {body} = document;
var WIDTH = 1024;
var RATIO = 3;

new Vue({
    el: "#app",
    data() {
        return {
            article: [{
                id: "",
                title: ""
            }],
            comments: [{
                id: "",
                author: ""
            }],
            defaultActive: '1',
            article_count: '',
            comments_count: '',
            tags_count: '',
            links_count: '',
            token: {name: ''},
            mobileStatus: false,
            sidebarStatus: true,
            sidebarFlag: ' openSidebar '
        }
    },
    methods: {
        init() {
            axios.get(this.buildPath("/article/findAllCount")).then(result => {
                this.article_count = result.data.data;
            });
            axios.get(this.buildPath("/comments/findAllCount")).then(result => {
                this.comments_count = result.data.data;
            });
            axios.get(this.buildPath("/tags/findAllCount")).then(result => {
                this.tags_count = result.data.data;
            });
            axios.get(this.buildPath("/links/findAllCount")).then(result => {
                this.links_count = result.data.data;
            });
            axios.get(this.buildPath("/admin/info")).then(result => {
                this.token.name = result.data.data.name;
            });
            axios.get(this.buildPath("/article/findAll")).then(result => {
                this.article = result.data.data;
            });
            axios.get(this.buildPath("/comments/findAll")).then(result => {
                this.comments = result.data.data;
            });
        },
        isMobile() {
            return body.getBoundingClientRect() - RATIO < WIDTH;
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
    mounted() {
        this.init();
        const isMobile = this.isMobile();
        if (isMobile) {
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    }
});