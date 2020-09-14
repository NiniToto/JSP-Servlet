<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public interface IBrowser{
		public String browserToExpression(String userAgent);
	}

	public static enum BrowserType {
		
		CHROME("Chrome", new IBrowser(){
			
			@Override
			public String browserToExpression(String userAgent){
				return String.format(PATTERN, userAgent);
			}
		}), 
		FIREFOX("Firefox", new IBrowser(){
			
			@Override
			public String browserToExpression(String userAgent){
				return String.format(PATTERN, userAgent);
			}
		}), 
		EXPLORER("Explorer", new IBrowser(){
			
			@Override
			public String browserToExpression(String userAgent){
				return String.format(PATTERN, userAgent);
			}
		});
		
		private static final String PATTERN = "<h4>%s을 사용하고 있음</h4>";
		private IBrowser browser;
		private String userAgent;
		
		private BrowserType(String userAgent,IBrowser browser){
			this.userAgent = userAgent;
			this.browser = browser;
		}
		
		public String browserToExpression(String userAgent){
			return browser.browserToExpression(userAgent);
		}
		
		public String getUserAgent(){
			return userAgent;
		}
	}
%>

<% 
	//String pattern = "<h4>%s을 사용하고 있음</h4>";
	String userAgent = request.getHeader("User-Agent");
	out.println(userAgent);
	BrowserType browserType = null;
	String result;
	
	if(userAgent != null && userAgent.contains("Chrome")){
		browserType = BrowserType.CHROME;
	}else if(userAgent != null && userAgent.contains("Firefox")){
		browserType = BrowserType.FIREFOX;
	}else if(userAgent != null && userAgent.contains("Gecko")){
		browserType = BrowserType.EXPLORER;
	}
	result = browserType.browserToExpression(browserType.toString());
	
	out.println(result);
	
%> 