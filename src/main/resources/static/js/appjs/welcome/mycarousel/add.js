$().ready(function() {
	validateRule();
	// initFileInput("fileCarousel", "mycarousel/fileUpload")
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/welcome/mycarousel/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}

function initFileInput(ctrlName, uploadUrl) {

	var control = $('#' + ctrlName);

	control.fileinput({

		language: 'zh', //设置语言

		uploadUrl: uploadUrl, //上传的地址

		allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀

		showUpload: false, //是否显示上传按钮

		showCaption: false,//是否显示标题

		browseClass: "btn btn-primary", //按钮样式

		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",

	});
}