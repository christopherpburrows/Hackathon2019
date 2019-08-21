package Helper;

import java.util.List;

public class suggestionJson {

	
	private String team;
	
	private String video;
	
	private List<String> upcoming_videos;

	public List<String> getUpcoming_videos() {
		return upcoming_videos;
	}

	public void setUpcoming_videos(List<String> upcoming_videos) {
		this.upcoming_videos = upcoming_videos;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	
	
}
