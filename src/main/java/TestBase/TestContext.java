package TestBase;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.IAssert;

public class TestContext {

	private WebDriver webDriver ;
	private Map<AssertionError, IAssert<?>> m_errors;
	private String filmNames ;
	private String filemDirectors;
	private String window;
	private String parentWindow;
	private String randomVideo;
	private String response;
	private List<String> upcomingVideos;
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public Map<AssertionError, IAssert<?>> getM_errors() {
		return m_errors;
	}

	public void setM_errors(Map<AssertionError, IAssert<?>> m_errors) {
		this.m_errors = m_errors;
	}

	public String getFilmNames() {
		return filmNames;
	}

	public void setFilmNames(String filmNames) {
		this.filmNames = filmNames;
	}

	public String getFilemDirectors() {
		return filemDirectors;
	}

	public void setFilemDirectors(String filemDirectors) {
		this.filemDirectors = filemDirectors;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public String getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(String parentWindow) {
		this.parentWindow = parentWindow;
	}

	public String getRandomVideo() {
		return randomVideo;
	}

	public void setRandomVideo(String randomVideo) {
		this.randomVideo = randomVideo;
	}

	public List<String> getUpcomingVideos() {
		return upcomingVideos;
	}

	public void setUpcomingVideos(List<String> upcomingVideos) {
		this.upcomingVideos = upcomingVideos;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
