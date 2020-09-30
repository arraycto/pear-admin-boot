layui.define(['jquery','layer','table'], function(exports) {

    const $ = layui.jquery,
        table = layui.table,
        layer = layui.layer;

    const common = {};

    /**
     * ajax文件下载
     *
     * @param url 请求地址
     * @param fileName 文件名称
     * @param param 参数对象，会转换为JSON
     * @param errMsg 发送错误时的提示信息
     */
    common.download = function(url, fileName, param, errMsg) {
        // 创建XMLHttpRequest对象
        const xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);
        // 设置发送数据的数据格式
        xhr.setRequestHeader('content-type', 'application/json');
        // 设置响应数据格式
        xhr.responseType = "blob";
        // 定义请求完成的处理参数
        xhr.onload = function () {
            if (this.status === 200) {
                // 返回200
                let blob = this.response;
                let reader = new FileReader();
                // 转换为base64，可以直接放入a --> href
                reader.readAsDataURL(blob);
                reader.onload = function (e) {
                    // 转换完成，创建一个a标签用于下载
                    let a = document.createElement('a');
                    a.style.display = 'none';
                    a.download = fileName;
                    a.href = e.target.result;
                    $("body").append(a);
                    a.click();
                    $(a).remove();
                }
            } else {
                layer.msg(errMsg, {icon: 5});
            }
        };
        // 发送请求
        xhr.send(JSON.stringify(param));
    };

    /**
     * 截取请求参数
     * @param name
     * @returns {string|null}
     */
    common.getUrlParam = function (name) {
        const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        const r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    };

    /**
     * 修改行数据
     * @param layId
     * @param row
     * @param data
     */
    common.updateLayTableRow = function (layId, row, data) {
        layui.$.extend(table.cache[layId][row],data);
    };

    /**
     * 根据id获取表单数据
     * @param formId
     * @returns {Object}
     */
    common.getFormData = function (formId) {
        const formData = {};
        const data = $('#' + formId).serializeArray();
        $.each(data, function() {
            formData[this.name] = this.value;
        });
        return formData;
    };

    exports('common', common);
});