package com.example.fcauserano.digitalnews.model.POJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NoticiaService {

    @GET("top-headlines")
    Call<NoticiaContainer> getNoticiasTop(@Query("sources") String sources,
                                                   @Query("country") String country,
                                                   @Query("category") String category,
                                                   @Query("pageSize") int pageSize,
                                                   @Query("page") int page,
                                                   @Query("q") String q);

    @GET("top-headlines")
    Call<NoticiaContainer> getNoticiasPorCategoria(@Query("category") String category,
                                                   @Query("country") String country);

    @GET("everything")
    Call<NoticiaContainer> getNoticiaseEerything(@Query("q") String q,
                                              @Query("sources") String sources,
                                              @Query("domains") String domains,
                                              @Query("from") String from,
                                              @Query("to") String to,
                                              @Query("language") String language,
                                              @Query("sortBy") String sortBy,
                                              @Query("pageSize") int pageSize,
                                              @Query("page") int page);

    @GET("sources")
    Call<FuenteContainer> getFuentes(@Query("category") String category,
                                     @Query("language") String language,
                                     @Query("country") String country);
}
