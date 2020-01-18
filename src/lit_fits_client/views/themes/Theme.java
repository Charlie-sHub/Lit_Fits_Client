package lit_fits_client.views.themes;

/**
 * Class for the Themes
 *
 * @author Carlos Mendez
 */
public class Theme {
    private String name;
    private String themeCss;

    public Theme() {
    }

    public Theme(String name, String themeCss) {
        this.name = name;
        this.themeCss = themeCss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThemeCss() {
        return themeCss;
    }

    public void setThemeCss(String themeCss) {
        this.themeCss = themeCss;
    }

    @Override
    public String toString() {
        return name;
    }
}
