<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.apache.commons.lang3.StringUtils, java.io.File"%>
<!DOCTYPE html>
<html>
<style>
	div{
	width : 40%;
	margin : 20px;
		float:left;
	}
</style>
<head>
<meta charset="UTF-8">
<title>WEB-INF/views/serverExplorer01.jsp</title>
<link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/skin-win8/ui.fancytree.min.css">
<script type="text/javascript" src = "<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/jquery.fancytree-all-deps.min.js"></script>
<script type="text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/jquery.fancytree-all.min.js"></script>
<script type="text/javascript">
	$(function(){
		var tree1 = null;
		var tree2 = null;
		//커맨드처리 관련 함수 분리
		var commandProcess = function(param){
			let command = param.command;
			let srcFile = param.data.otherNode.key;
			let destFolder = null;
			if(param.node){
				destFolder = param.node.key;
			}
			 $.ajax({
					data : {
						command : command,
						srcFile : srcFile,
						destFolder : destFolder
					},
					method : "post",
					dataType : "json", //Accept, Content-Type
					success : function(resp) {
						if(!resp.success){
							return;
						}
						if(command == "COPY"){
							param.data.otherNode.copyTo(param.node);
		            	}else if(command == "MOVE"){
		            		param.data.otherNode.moveTo(param.node, param.data.hitMode);
		            	}else{
		            		param.data.otherNode.remove();
		            	}
					},
					error : function(xhr) {
						console.log(xhr);
					}
			});
		}
		//fancytree
		$("#tree1").fancytree({
			  extensions: ["dnd", "edit"],
		      selectMode: 1,
		      lazyLoad: function(event, data){
		        data.result = {
		        		url: "<%=request.getContextPath()%>/serverExplorer01.do?url="+data.node.key
		        		//get Method를 이용하기 때문에 쿼리 스트링으로 key값을 넘겨준다.
		        };
		      },
		      
		      init : function(event, data){
		    	  tree1 = data.tree.getFirstChild().setTitle("webStudy:\\");
		      },
		      
		      dnd: {
		          autoExpandMS: 400,
		          focusOnClick: true,
		          preventVoidMoves: true, 
		          preventRecursiveMoves: true, 
		          dragStart: function(node, data) {
	              return true;
		     },
		     
		          dragEnter: function(node, data) {

		        	let pass = false;
					pass = node.folder && node != data.otherNode.parent && node.parent != data.otherNode.parent;
		             return pass;
		             
		          },
		          
		          dragDrop: function(node, data) {
		        	  let param = {
		        			  node : node,
		        			  data : data,
		        			  command : data.originalEvent.ctrlKey?"COPY":"MOVE"
		        	  }
		        	  commandProcess(param);
		          }
		        }
		    });
		
		//delete 키 입력 이벤트 처리
		$(window).on("keyup", function(event){
			if(event.keyCode != 46) return;
			let pass = confirm("정말 지울까요?");
			if(pass){
				let srcFile = tree1.getActiveNode();
				commandProcess({
					command : "DELETE",
					data : {
						otherNode : srcFile
					}
				});
			}
		});
		$("#tree2").fancytree({
			  extensions: ["dnd", "edit"],
		      selectMode: 1,
		      lazyLoad: function(event, data){
		        data.result = {
		        		url: "<%=request.getContextPath()%>/serverExplorer01.do?url="+data.node.key
		        		//get Method를 이용하기 때문에 쿼리 스트링으로 key값을 넘겨준다.
		        };
		      },
		      
		      init : function(event, data){
		    	  tree2 = data.tree.getFirstChild().setTitle("dummy:\\");
		      },
		      
		      dnd: {
		          autoExpandMS: 400,
		          focusOnClick: true,
		          preventVoidMoves: true, 
		          preventRecursiveMoves: true, 
		          dragStart: function(node, data) {
	              return true;
		     },
		     
		          dragEnter: function(node, data) {

		        	let pass = false;
					pass = node.folder && node != data.otherNode.parent && node.parent != data.otherNode.parent;
		             return pass;
		             
		          },
		          
		          dragDrop: function(node, data) {
		        	  let param = {
		        			  node : node,
		        			  data : data,
		        			  command : data.originalEvent.ctrlKey?"COPY":"MOVE"
		        	  }
		        	  commandProcess(param);
		          }
		        }
		    });
		
		//delete 키 입력 이벤트 처리
		$(window).on("keyup", function(event){
			if(event.keyCode != 46) return;
			let pass = confirm("정말 지울까요?");
			if(pass){
				let srcFile = tree1.getActiveNode();
				commandProcess({
					command : "DELETE",
					data : {
						otherNode : srcFile
					}
				});
			}
		});
	})
</script>
</head>
<body>
<h4>Model2 구조를 이용해서 serverExplorer 따라서 코딩하기</h4>
<hr>
<pre>
** 현재 컨텍스트에 있는 리소스들을 대상으로 한 익스플로러 구현
1. 파라미터가 없는 경우, 컨텍스트 루트의 모든 자식 리소스를 목록으로 제공함.
2. 이후 모든 처리는 이클립스의 project explorer 뷰를 참고하여, 구현함.
</pre>
<hr>
<div id = "tree1">
	<ul>
		<li id = "/" class = "lazy folder"><%=request.getContextPath() %></li>
	</ul>
</div>
<div id = "tree2">
	<ul>
		<li id = "/" class = "lazy folder"><%=request.getContextPath() %></li>
	</ul>
</div>
</body>
</html>