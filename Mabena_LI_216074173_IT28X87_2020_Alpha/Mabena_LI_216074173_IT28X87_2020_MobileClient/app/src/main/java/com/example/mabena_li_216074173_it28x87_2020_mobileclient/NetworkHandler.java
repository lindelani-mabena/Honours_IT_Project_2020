package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface NetworkHandler {
    @POST("authentication/register")
    @FormUrlEncoded
    Call<RegisterModel> Register(@Field("user_name") String user_name,
                              @Field("user_title") String user_title,
                              @Field("user_emailaddress") String user_emailaddress,
                              @Field("user_password") String user_password,
                              @Field("user_confirmpassword") String user_confirmpassword);
    @POST("authentication/register/manager")
    @FormUrlEncoded
    Call<RegisterManagerModel> registerManager(@Field("user_id") String UserID);
    @POST("authentication/register/seeker")
    @FormUrlEncoded
    Call<Void> registerResSeeker(@Field("user_id") String user_id,
                                       @Field("res_seeker_gender") String gender,
                                       @Field("res_seeker_DateofBirth") String DateOfBirth,
                                       @Field("res_seeker_race") String Race,
                                       @Field("res_seeker_religion") String Religion,
                                       @Field("res_seeker_homelanguage") String HomeLanguage,
                                       @Field("res_seeker_employmentstatus_id") String res_seeker_employmentstatus_id);
    @POST("authentication/login")
    @FormUrlEncoded
    Call<LoginObject> Login(@Field ("user_emailaddress") String username,
                            @Field ("user_password") String password);
    @GET("authentication/usertype/{id}")
    Call<User>getUserType(@Path("id") String id);
    @FormUrlEncoded
    @POST("accommodation/address")
    Call<AddressObject> AddAddress(@Field ("address_line1") String AddressLine1,
                                   @Field ("address_line2") String AddressLine2,
                                   @Field ("address_town") String AddressTown,
                                   @Field ("address_city") String AddressCity,
                                   @Field("address_province") String AddressProvince,
                                   @Field("address_postalcode") String AddressPostalCode,
                                   @Field("address_phonenumber") String AddressPhoneNumber);

    @GET("accommodation/address/{id}")
    Call<List<Address>> GetAddress(@Path("id") int id);
    @FormUrlEncoded
    @POST("accommodation/accommodation")
    Call<Void> AddAccommodation(@Field("accommodation_name") String accommodation_name,
                                @Field("accommodation_address_id") int accommodation_address_id,
                                @Field("accommodation_gym") int accommodation_gym,
                                @Field("accommodation_security") int accommodation_security,
                                @Field("accommodation_washingmachines") int accommodation_washingmachines,
                                @Field("accommodation_wifi") int accommodation_wifi,
                                @Field("manager_id") String manager_id,
                                @Field("accommodation_type_id") int accommodation_type_id);
    @FormUrlEncoded
    @PUT("accommodation/accommodation/update/{id}")
    Call<Void> UpdateAccommodation(
                                @Path("id") int id,
                                @Field("accommodation_gym") int accommodation_gym,
                                @Field("accommodation_security") int accommodation_security,
                                @Field("accommodation_washingmachines") int accommodation_washingmachines,
                                @Field("accommodation_wifi") int accommodation_wifi,
                                @Field("accommodation_type_id") int accommodation_type_id);

    @GET("accommodation/accommodations/manager/{id}")
    Call<List<Accommodation>> GetManagerAccommodations(@Path("id") String id);
    @GET("reviews/reviews/reviewer/{id}")
    Call<List<Review>> GetSeekerReviews(@Path("id") int id);
    @FormUrlEncoded
    @PUT("authentication/profile/update/{user_id}")
    Call<Void> UpdateProfile(@Path("user_id") String id,
                             @Field("res_seeker_religion") String res_seeker_religion,
                             @Field("res_seeker_employmentstatus_id") String res_seeker_employmentstatus_id);
    @GET("accommodation/accommodations")
    Call<List<Accommodation>> GetAllAccommodations();

    @FormUrlEncoded
    @POST("accommodation/accommodations/filtered")
    Call<List<Accommodation>> GetFilteredAccommodations(@Field("accommodation_gym") int accommodation_gym,
                                                        @Field("accommodation_security") int accommodation_security,
                                                        @Field("accommodation_washingmachines") int accommodation_washingmachines,
                                                        @Field("accommodation_wifi") int accommodation_wifi);


    @GET("reviews/review/accommodation/{id}")
    Call<List<Review>> getAllAccommodationReviews(@Path("id") int id);
    @FormUrlEncoded
    @POST("reviews/review")
    Call<Review> addNewReview(@Field("review_rating") int review_rating,
                              @Field("review_description")  String review_description,
                              @Field("review_reviewer_id") String review_Reviewer_id,
                              @Field("review_accommodation_reviewed") int review_Accommodation_Reviewed);
    @FormUrlEncoded
    @PUT("reviews/review/{id}")
    Call<Review> EditReview(@Path("id") int id,
                              @Field("review_description")  String review_description,
                              @Field("review_rating") int review_rating);

    @DELETE("reviews/review/{id}")
    Call<Review> DeleteReview(@Path("id") int id);

    @DELETE("accommodation/accommodation/delete/{id}")
    Call<Void> DeleteAccommodation(@Path("id") int id);
}
