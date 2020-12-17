package movie.model;

public class MovieAdvisor {
	
	public String getAdvice(String movie) {
		String msg = null;
			
		if(movie.equals("미션임파서블5")) {
			msg = "톰크루즈 주연의 대작영화";
		}else if(movie.equals("스타워즈")) {
			msg = "당신이 선택한 영화는 스타워즈 입니다";
		}else if(movie.equals("존윅3")) {
			msg = "당신이 선택한 영화는 존윅3 입니다";
		}else if(movie.equals("분노의질주")) {
			msg = "당신이 선택한 영화는 분노의질주 입니다";
		}
	return msg;
	}
}
