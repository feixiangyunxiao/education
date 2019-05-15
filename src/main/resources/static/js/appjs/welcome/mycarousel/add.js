$().ready(function() {
	validateRule();
	layui.use('upload', function () {
		var upload = layui.upload;
		//执行实例
		var uploadInst = upload.render({
			elem: '#test2', //绑定元素
			url: '/common/sysFile/upload', //上传接口
			size: 2000,
			accept: 'file',
			done: function (r) {
				layer.msg(r.msg);
				if(r.code == 0){
					//fileName=/files/4e4a6309-06ed-471d-a6ef-24a9e50a9b0b.jpg
					$("#picname").val(r.fileName);
					var str='<img  src="http://'+window.location.host+r.fileName+'" style="width: 100px;height: 100px;">';
					console.log("http://" +window.location.host+r.fileName)
					$("#ps").html(str)
				}
			},
			error: function (r) {
				layer.msg(r.msg);
			}
		});
	});
	// initFileInput("fileCarousel", "mycarousel/fileUpload")
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		type : "POST",
		url : "/welcome/mycarousel/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : true,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			console.log(data)
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			} else {
				console.log('我错了')
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			title : {
				required : true
			},

			href : {
				required : true
			}

		},
		messages : {
			title : {
				required : icon + "请输入标题"
			},
			href : {
				required : icon + "请输入要跳转的地址"
			}
		}
	})
}

function initFileInput(ctrlName, uploadUrl) {

	var control = $('#' + ctrlName);

	console.log(uploadUrl)

	control.fileinput({

		language: 'zh', //设置语言

		uploadUrl: uploadUrl, //上传的地址

		uploadAsync : true,

		allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀

		showUpload: false, //是否显示上传按钮

		showCaption: false,//是否显示标题

		browseClass: "btn btn-primary", //按钮样式

		enctype: 'multipart/form-data',

		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",

	})
	control.on("fileuploaded", function (event, data) {

	});
}