<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addCategory(){
				window.location.href = "${pageContext.request.contextPath}/";
			}
			function removeFromCart(cid){
				if(confirm("确定删除?")){
					location.href="${ pageContext.request.contextPath }/adminCategory?method=del&id="+cid;
				}
			}
		</script>
		
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>分类列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="btn btn-success btn-sm" onclick="addCategory()">
							
&#28155;&#21152;
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 30px; BACKGROUND-COLOR: #afd1f3; font-family: 微软雅黑   ">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										分类名称
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<c:forEach var="c" items="${ list }" varStatus="vs">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 40px" align="center" 
												width="18%">
												${vs.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 40px" align="center"
												width="17%">
												${c.cname }
											</td>
											<td align="center" style="HEIGHT: 40px">
												 <a href="${ pageContext.request.contextPath }/adminCategory?method=edit&id=${c.cid}">
					
														<button type="button" class="btn btn-success btn-sm" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 编辑</button>
												</a> 
											
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<%-- <a href="${ pageContext.request.contextPath }/adminCategory?method=del&id=${c.cid}"> 
													<button type="button" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>
													
												 </a>  --%>
												<a href="javascript:void(0);" onclick="removeFromCart('${c.cid}')" class="delete"><button type="button" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button></a>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
	
</HTML>

