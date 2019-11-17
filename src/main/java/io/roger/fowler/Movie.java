package io.roger.fowler;

public class Movie {

	private String title;
	private MovieType movieType;

	public Movie(String title, MovieType movieType) {
		this.title = title;
		this.movieType = movieType;
	}

	public MovieType getMovieType() {
		return this.movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public String getTitle() {
		return this.title;
	}
}
