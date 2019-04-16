var {body} = document;
var WIDTH = 1024;
var RATIO = 3;

new Vue({
    el: "#app",
    data() {
        return {
            checked: false,
            login: {
                username: '',
                password: '',
                remember: false
            },
            flag: true,
            loading: {}
        }
    },
    methods: {
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: "拼命加载中.....",
                spinner: 'el-icon-loading'
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
        },
        submitForm() {
            this.$refs.login.validate(valid => {
                if (valid) {
                    this.loadings();
                    axios.post(this.buildPath('/admin/login'), {
                        username: this.login.username.trim(),
                        password: this.login.password.trim(),
                        remember: this.login.remember
                    }).then(result => {
                        this.loading.close();
                        if (result.status === 200 && result.data.code === 0) {
                            // window.location.href = result.body.redirecUrl
                            window.location.href = this.buildPath('/admin');
                        } else {
                            this.$emit('submit-form', this.$message({
                                message: result.body.data,
                                type: 'warning',
                                duration: 6000
                            }))
                        }
                    });
                } else {
                    this.$emit("submit-form", this.$message({
                        message: "输入的信息有误",
                        type: 'warning',
                        duration: 6000
                    }))
                }
            })
        },
    }
});