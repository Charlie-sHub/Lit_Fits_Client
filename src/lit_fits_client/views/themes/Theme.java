package lit_fits_client.views.themes;

/**
 * Class for the Themes
 *
 * @author Carlos Mendez
 */
public class Theme {
    private String themeCss;

    public Theme() {
    }

    public Theme(String themeCss) {
        this.themeCss = themeCss;
    }

    public String getThemeCss() {
        return themeCss;
    }

    public void setThemeCss(String themeCss) {
        this.themeCss = themeCss;
    }

    @Override
    public String toString() {
        return themeCss.substring(0, themeCss.length() - 4);
    }
}
