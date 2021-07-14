package ua.com.alevel.templates;

public class MessageTemplates {
    public static final String NOT_FOUND_MESSAGE = "Results not found. Check the spelling and try again.";
    public static final String SUCCESS_MESSAGE = "Search finished";
    public static final String ASK_MEDS_NAME_MESSAGE = "Please, enter medicine's name";
    public static final String ASK_MEDS_FORM_MESSAGE = "Please choose medicine's form (type)";
    public static final String ASK_ADDRESS_MESSAGE = "Please enter your current address";
    public static final String WRONG_ANSWER_CHOICE = "Wrong choice. Right options: Y - yes, N - no";
    public static final String WELCOME_MESSAGE = "Hello! This bot will help you find nearest pharmacy" +
            " where you can buy medicine that you need. \nFind medicines - find any medicine by it`s name" +
            " in nearest pharmacies. \nSee instruction - enter name of the medicine and see an instruction to it. \n" +
            "Search history - names of the medicines that you have searched";

    public static String askAboutAddressChange(String current) {
        return "Your current address is " + current + ". Do you want to change it? \n Y - yes, N - no";
    }

}
