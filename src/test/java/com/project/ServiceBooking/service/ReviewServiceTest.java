package com.project.ServiceBooking.service;

import com.project.ServiceBooking.data.Review;
import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.ServicesCategory;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.repositories.ReviewRepository;
import com.project.ServiceBooking.services.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewServiceTest {
    private int reviewId;
    private final ReviewRepository reviewRepository = Mockito.mock(ReviewRepository.class);
    private final ReviewService reviewService = new ReviewService(reviewRepository);

    @Test
    public void findByIdTest() {

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(getTestReview()));
        Review review = reviewService.findById(reviewId);
        assertEquals(22, review.getId());
    }

    @Test
    public void findAllTest() {
        when(reviewRepository.findAll()).thenReturn(getTestReviewList());
        assertEquals(2, reviewService.findAll().size());
    }
    @Test
    public void saveUserTest(){
        Review review = getTestReview();
        when(reviewRepository.save(review)).thenReturn(review);
        assertEquals(getTestReview().getId(), reviewService.saveReview(review).getId());
    }
    @Test
    public void deleteByIdTest() {
        assertDoesNotThrow(()->reviewService.deleteById(reviewId));
    }

    @Test
    public void findByIdServicesTest(){
        when(reviewRepository.findByidServices(reviewId)).thenReturn((getTestReviewList()));
        assertEquals(2, reviewService.findByidServices(reviewId).size());
    }

    @Test
    public void findAvgTest(){

        when(reviewRepository.findByidServices(reviewId)).thenReturn((getTestReviewList()));
        float actualAvg = reviewService.findAvgRating(reviewId);

        assertEquals(22, actualAvg, 0.01);
    }

    private Review getTestReview() {
        Review review = new Review();
        review.setId(22);
        review.setRating(22);
        review.setComment("Ruhm");
        review.setTime_review(LocalDateTime.now());
        review.setIdServices(new Service());
        review.setUsersIdUsers(new User());
        return review;
    }

    private List<Review> getTestReviewList() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(getTestReview());
        Review review = getTestReview();
        review.setId(33);
        review.setRating(22);
        reviewList.add(review);
        return reviewList;
    }
}
