package bridge;

import java.util.Arrays;

public enum BridgeLocation {
    UP_FALSE(1, false, "U", "X"),
    UP_TRUE(1, true, "U", "O"),
    DOWN_FALSE(0, false, "D", "X"),
    DOWN_TRUE(0, true, "D", "O");

    public static final String UN_CHOSEN_LOCATION = " ";
    public static final String LOCATION_NUMBER_ERROR = "[ERROR] 존재하지 않는 정보입니다.";
    public static final String LOCATION_ERROR = "[ERROR] U 또는 D를 입력해 주시기 바랍니다.";

    private final int locationNumber;
    private final boolean correctLocation;
    private final String location;
    private final String stateLocation;

    BridgeLocation(int locationNumber, boolean correctLocation, String location, String stateLocation) {
        this.locationNumber = locationNumber;
        this.correctLocation = correctLocation;
        this.location = location;
        this.stateLocation = stateLocation;
    }

    public static BridgeLocation createLocation(int locationNumber) {
        return Arrays.stream(values())
                .filter(locationValue -> locationValue.locationNumber == locationNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(LOCATION_NUMBER_ERROR));
    }

    public static BridgeLocation createLocation(String location, boolean correctLocation) {
        return Arrays.stream(values())
                .filter(locationValue -> isLocation(locationValue, location)
                        && isCorrectLocation(locationValue, correctLocation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(LOCATION_ERROR));
    }

    private static boolean isCorrectLocation(BridgeLocation locationValue, boolean correctLocation) {
        return locationValue.correctLocation == correctLocation;
    }

    private static boolean isLocation(BridgeLocation locationValue, String location) {
        return locationValue.location.equals(location);
    }

    public String getLocation() {
        return location;
    }

    public boolean getCorrectLocation() {
        return correctLocation;
    }

    public String getStateLocation(String location) {
        if (this.location.equals(location)) {
            return stateLocation;
        }
        return UN_CHOSEN_LOCATION;
    }
}
