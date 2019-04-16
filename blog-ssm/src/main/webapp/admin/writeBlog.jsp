<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>写博客页面</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/static/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript">
        function submitData() {
            var title = $("#title").val();
            var blogTypeId = $("#blogTypeId").combobox("getValue");
            var content = UE.getEditor('editor').getContent();
            var keyWord = $("#keyWord").val();

            if (title == null || title === '') {
                alert("请输入标题!");
            } else if (blogTypeId == null || blogTypeId === '') {
                alert("请选择博客类别!");
            } else if (content == null || content === '') {
                alert("请输入内容!");
            } else {
                $.post('${pageContext.request.contextPath}/admin/blog/save', {
                    'title': title,
                    'blogType.id': blogTypeId,
                    'content': content,
                    'contentNotTag': UE.getEditor('editor').getContentTxt(),
                    'summary': UE.getEditor('editor').getContentTxt().substr(0, 155),
                    'keyWord': keyWord
                }, function (result) {
                    if (result.success) {
                        alert("博客发布成功!");
                        resetValue();
                    } else {
                        alert("博客发布失败!");
                    }
                }, "json");
            }
        }

        function resetValue() {
            $("#title").val('');
            $('#blogTypeId').combobox('setValue', '');
            UE.getEditor('editor').setContent('');
            $('#keyWord').val('');
        }
    </script>
</head>
<body>
    <div id="p" class="easyui-panel" title="编写博客" style="padding: 10px;">
        <table cellspacing="20px">
            <tr>
                <td>博客标题</td>
                <td><input type="text" id="title" name="title"></td>
            </tr>
            <tr>
                <td>所属类别:</td>
                <td>
                    <select name="blogType.id" id="blogTypeId">
                        <option value="">请选择博客类别....</option>
                        <c:forEach var="blogType" items="${blogTypeCountList}">
                            <option value="${blogType.id}">${blogType.typeName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>博客内容:</td>
                <td>
                    <script id="editor" type="text/plain"></script>
                </td>
            </tr>
            <tr>
                <td>关键字:</td>
                <td><input type="text" id="keyWord" name="keyWord">&nbsp;&nbsp;(多个关键字用空格隔开)</td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit">
                        发布博客
                    </a>
                </td>
            </tr>
        </table>
    </div>
<script type="text/javascript">
    var ue = UE.getEditor('editor');
</script>
</body>
</html>
