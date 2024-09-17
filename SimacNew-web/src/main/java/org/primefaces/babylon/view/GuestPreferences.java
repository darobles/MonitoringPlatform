package org.primefaces.babylon.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

    private String menuMode = "layout-overlay";

    private String theme = "blue-light";
    //private String theme = "indigo-dark";

    private String layout = "lightblue";
    //private String layout = "indigo";

    private boolean darkMenu = false;

    private String profileMode = "inline";

    private boolean groupedMenu = false;

    private boolean darkLogo;

    private boolean orientationRTL;
    
    private List<ComponentTheme> componentThemes = new ArrayList();
    
    private String top = "50%";
    
    @PostConstruct
    public void init() {
        componentThemes.add(new ComponentTheme("Amber Accent", "amber-accent", "amber-accent.svg"));
        componentThemes.add(new ComponentTheme("Amber Light", "amber-light", "amber-light.svg"));
        componentThemes.add(new ComponentTheme("Amber Dark", "amber-dark", "amber-dark.svg"));
        componentThemes.add(new ComponentTheme("Blue Accent", "blue-accent", "blue-accent.svg"));
        componentThemes.add(new ComponentTheme("Blue Light", "blue-light", "blue-light.svg"));
        componentThemes.add(new ComponentTheme("Blue Dark", "blue-dark", "blue-dark.svg"));
        componentThemes.add(new ComponentTheme("Blue Grey Accent", "bluegrey-accent", "bluegrey-accent.svg"));
        componentThemes.add(new ComponentTheme("Blue Grey Light", "bluegrey-light", "bluegrey-light.svg"));
        componentThemes.add(new ComponentTheme("Blue Grey Dark", "bluegrey-dark", "bluegrey-dark.svg"));
        componentThemes.add(new ComponentTheme("Brown Accent", "brown-accent", "brown-accent.svg"));
        componentThemes.add(new ComponentTheme("Brown Light", "brown-light", "brown-light.svg"));
        componentThemes.add(new ComponentTheme("Brown Dark", "brown-dark", "brown-dark.svg"));
        componentThemes.add(new ComponentTheme("Cyan Accent", "cyan-accent", "cyan-accent.svg"));
        componentThemes.add(new ComponentTheme("Cyan Light", "cyan-light", "cyan-light.svg"));
        componentThemes.add(new ComponentTheme("Cyan Dark", "cyan-dark", "cyan-dark.svg"));
        componentThemes.add(new ComponentTheme("Deep Orange Accent", "deeporange-accent", "deeporange-accent.svg"));
        componentThemes.add(new ComponentTheme("Deep Orange Light", "deeporange-light", "deeporange-light.svg"));
        componentThemes.add(new ComponentTheme("Deep Orange Dark", "deeporange-dark", "deeporange-dark.svg"));
        componentThemes.add(new ComponentTheme("Deep Purple Accent", "deeppurple-accent", "deeppurple-accent.svg"));
        componentThemes.add(new ComponentTheme("Deep Purple Light", "deeppurple-light", "deeppurple-light.svg"));
        componentThemes.add(new ComponentTheme("Deep Purple Dark", "deeppurple-dark", "deeppurple-dark.svg"));
        componentThemes.add(new ComponentTheme("Green Accent", "green-accent", "green-accent.svg"));
        componentThemes.add(new ComponentTheme("Green Light", "green-light", "green-light.svg"));
        componentThemes.add(new ComponentTheme("Green Dark", "green-dark", "green-dark.svg"));
        componentThemes.add(new ComponentTheme("Indigo Accent", "indigo-accent", "indigo-accent.svg"));
        componentThemes.add(new ComponentTheme("Indigo Light", "indigo-light", "indigo-light.svg"));
        componentThemes.add(new ComponentTheme("Indigo Dark", "indigo-dark", "indigo-dark.svg"));
        componentThemes.add(new ComponentTheme("Light Blue Accent", "lightblue-accent", "lightblue-accent.svg"));
        componentThemes.add(new ComponentTheme("Light Blue Light", "lightblue-light", "lightblue-light.svg"));
        componentThemes.add(new ComponentTheme("Light Blue  Dark", "lightblue-dark", "lightblue-dark.svg"));
        componentThemes.add(new ComponentTheme("Light Green Accent", "lightgreen-accent", "lightgreen-accent.svg"));
        componentThemes.add(new ComponentTheme("Light Green Light", "lightgreen-light", "lightgreen-light.svg"));
        componentThemes.add(new ComponentTheme("Light Green  Dark", "lightgreen-dark", "lightgreen-dark.svg"));
        componentThemes.add(new ComponentTheme("Lime Accent", "lime-accent", "lime-accent.svg"));
        componentThemes.add(new ComponentTheme("Lime Light", "lime-light", "lime-light.svg"));
        componentThemes.add(new ComponentTheme("Lime Dark", "lime-dark", "lime-dark.svg"));
        componentThemes.add(new ComponentTheme("Orange Accent", "orange-accent", "orange-accent.svg"));
        componentThemes.add(new ComponentTheme("Orange Light", "orange-light", "orange-light.svg"));
        componentThemes.add(new ComponentTheme("Orange Dark", "orange-dark", "orange-dark.svg"));
        componentThemes.add(new ComponentTheme("Pink Accent", "pink-accent", "pink-accent.svg"));
        componentThemes.add(new ComponentTheme("Pink Light", "pink-light", "pink-light.svg"));
        componentThemes.add(new ComponentTheme("Pink Dark", "pink-dark", "pink-dark.svg"));
        componentThemes.add(new ComponentTheme("Purple Accent", "purple-accent", "purple-accent.svg"));
        componentThemes.add(new ComponentTheme("Purple Light", "purple-light", "purple-light.svg"));
        componentThemes.add(new ComponentTheme("Purple Dark", "purple-dark", "purple-dark.svg"));
        componentThemes.add(new ComponentTheme("Teal Accent", "teal-accent", "teal-accent.svg"));
        componentThemes.add(new ComponentTheme("Teal Light", "teal-light", "teal-light.svg"));
        componentThemes.add(new ComponentTheme("Teal Dark", "teal-dark", "teal-dark.svg"));
        componentThemes.add(new ComponentTheme("Yellow Accent", "yellow-accent", "yellow-accent.svg"));
        componentThemes.add(new ComponentTheme("Yellow Light", "yellow-light", "yellow-light.svg"));
        componentThemes.add(new ComponentTheme("Yellow Dark", "yellow-dark", "yellow-dark.svg"));
        componentThemes.add(new ComponentTheme("Auter Theme", "auter", "auter.svg"));
    }

    public String getLayout() {
        return this.layout;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
        this.layout = this.theme.split("-")[0];
        this.darkLogo = this.layout.equals("lime") || this.layout.equals("yellow");
    }

    public void changeTheme(String theme, boolean dark) {
        this.setTheme(theme);
        this.darkMenu = dark;
    }

    public String getMenuMode() {
        return this.menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;

        if (this.menuMode.equals("layout-horizontal")) {
            this.groupedMenu = true;
        }
    }

    public boolean isDarkMenu() {
        return this.darkMenu;
    }

    public void setDarkMenu(boolean darkMenu) {
        this.darkMenu = darkMenu;
    }

    public String getMenuColor() {
        return this.darkMenu ? "layout-menu-dark" : "layout-menu-light";
    }

    public String getProfileMode() {
        return this.profileMode;
    }

    public void setProfileMode(String profileMode) {
        this.profileMode = profileMode;
    }

    public boolean isGroupedMenu() {
        return this.groupedMenu;
    }

    public void setGroupedMenu(boolean value) {
        this.groupedMenu = value;
        this.menuMode = "layout-static";
    }

    public boolean isDarkLogo() {
        return this.darkLogo;
    }

    public boolean isOrientationRTL() {
        return orientationRTL;
    }

    public void setOrientationRTL(boolean orientationRTL) {
        this.orientationRTL = orientationRTL;
    }

    public List<ComponentTheme> getComponentThemes() {
        return componentThemes;
    }

    public void setComponentThemes(List<ComponentTheme> componentThemes) {
        this.componentThemes = componentThemes;
    }
    
    public class ComponentTheme {
        String name;
        String file;
        String image;

        public ComponentTheme(String name, String file, String image) {
            this.name = name;
            this.file = file;
            this.image = image;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getImage() {
            return this.image;
        }
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
    
    
    
    
}
