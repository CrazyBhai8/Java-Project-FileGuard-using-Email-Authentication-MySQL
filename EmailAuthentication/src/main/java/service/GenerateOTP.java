package service;

import java.util.Random;
// prepoce:- The GenerateOTP class is designed to generate a 4-digit OTP (One-Time Password) using a random number generator. Here are some improvements and suggestions:


public class GenerateOTP {
	public static String getOTP() {
		Random random = new Random();
		return String.format("%04d", random.nextInt(10000)) ;
	}
}
