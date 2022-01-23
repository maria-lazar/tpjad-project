package tpjad.server.user.dtos;


public class UserDTO {
    private String name;
    private String badgeNumber;
    private String userType;
    private String password;
    private String team;

    public UserDTO() {

    }


    public UserDTO(String name, String badgeNumber, String userType, String password, String team) {
        this.name = name;
        this.badgeNumber = badgeNumber;
        this.userType = userType;
        this.password = password;
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", userType='" + userType + '\'' +
                ", password='" + password + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
