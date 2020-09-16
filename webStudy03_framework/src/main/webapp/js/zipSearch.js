/**
 * 
 */
	$.fn.zipSearch=function(){
		var zipArea = this;
		var timer = null;
		var timerJob = null;
		var div1 = null;
		function makeZipArea(){
			let modalHeader = $("<div>").addClass("modal-header").append($("<h5>").text("주소 검색"));
			let infoTag = $("<p>").text("검색할 주소를 입력해주세요!");
			let inputTag = $("<input>").attr({
				"id" : "zipInput",
				"type" : "text",
				"placeholder" : "주소 입력"
			});
			let modalBody = $("<div>").addClass("modal-body").append(infoTag, inputTag);
			let yesBtn = $("<button>").addClass("btn btn-primary").text("검색").prop("id", "yesBtn");
			let noBtn = $("<button>").addClass("btn btn-primary").text("닫기").prop("id", "noBtn");
			let modalFooter = $("<div>").addClass("modal-footer").append(yesBtn, noBtn);
			let modalContent = $("<div>").addClass("modal-content").append(modalHeader, modalBody, modalFooter);
			div1 = $("<div>").attr({
				"class":"modal fade"
				, "id" :"msgArea"
				, tabindex :"-1"
			}).append($("<div>").addClass("modal-dialog").append(modalContent));
			
			div1.modal({show:false});
			$("#msgArea").modal("show");
			
			let clickHandler = function(){
				let id = this.id;
				if(id=="yesBtn"){
					init();
				}
				div1.modal("hide");
			}; 
			yesBtn.on("click", clickHandler);
			noBtn.on("click", clickHandler);
		}
		makeZipArea();
		return this;
	}