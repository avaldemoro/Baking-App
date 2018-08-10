package co.asterv.ad_bakingapp.utils;

public class Constant {

    public final static int TOAST_DURATION = 400;

    /*** TITLES/UI ***/
    public final static String DETAILS_TITLE = "Details";
    public final static String MAIN_TITLE = "For Goodness Bakes";

    /*** PARCELABLE KEYS***/
    public final static String RECIPE_KEY = "recipe";
    public final static String STEP_KEY = "step";
    public final static String GET_RECIPE_INFO_KEY = "recipeInfo";
    public final static String BUNDLE_KEY = "bundle";

    /*** JSON KEYS ***/
    final static String BAKING_JSON_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public final static String NAME_KEY = "name";
    public final static String ID_KEY = "id";
    public final static String INGREDIENTS_KEY = "ingredients";
    public final static String INGREDIENTS_QUANTITY_KEY = "quantity";
    public final static String INGREDIENTS_MEASURE_KEY = "measure";
    public final static String INGREDIENT_NAME_KEY = "ingredient";
    public final static String STEPS_KEY = "steps";
    public final static String STEP_SHORT_DESC_KEY = "shortDescription";
    public final static String STEP_LONG_DESC_KEY = "description";
    public final static String STEP_VIDEO_URL = "videoURL";
    public final static String STEP_THUMB_URL = "thumbnailURL";
    public final static String SERVINGS_KEY = "servings";

    /*** ERROR MESSAGES ***/
    public final static String NO_INTERNET_TEXT = "Oh no! No internet connection.";

    /*** COMMANDS ***/
    public final static String INTERNET_CHECK_COMMAND = "/system/bin/ping -c 1 8.8.8.8";
}
