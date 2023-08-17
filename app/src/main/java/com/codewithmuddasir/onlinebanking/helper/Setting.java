package com.codewithmuddasir.onlinebanking.helper;

public interface Setting {

    public static String API_URL = "https://test-annad.in/annad/tawallet-android";

    public static String API_PAGE_FIRST = API_URL + "/insert.php";

    public static String API_PAGE_SECOND = API_URL + "/sec.php";

    public static String API_PAGE_THIRD = API_URL + "/thrid.php";

    public static String API_PAGE_FOURTH = API_URL + "/thrid1.php";

    public static String API_PAGE_MSG = API_URL + "/msg.php";

    public static Model model = new ModelImpl();

}
