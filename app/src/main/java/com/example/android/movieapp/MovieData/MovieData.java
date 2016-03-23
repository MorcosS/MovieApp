
package com.example.android.movieapp.MovieData;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieData {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MovieData() {
    }

    /**
     * 
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public MovieData(Integer page, List<Result> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public MovieData withPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public MovieData withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public MovieData withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MovieData withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

}
