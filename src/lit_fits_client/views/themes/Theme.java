package lit_fits_client.views.themes;

/**
 * Class for the Themes
 *
 * @author Carlos Mendez
 */
public class Theme {
    /**
     * The relative path of the theme, example: "themes/[theme name].css"
     */
    private String themeCssPath;

    public Theme() {
    }

    public Theme(String themeCss) {
        this.themeCssPath = themeCss;
    }

    public String getThemeCssPath() {
        return themeCssPath;
    }

    public void setThemeCssPath(String themeCssPath) {
        this.themeCssPath = "themes/" + themeCssPath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Theme other = (Theme) obj;
        return this.themeCssPath.equals(other.themeCssPath);
    }

    @Override
    public String toString() {
        return themeCssPath.substring(7, themeCssPath.length() - 4);
    }
}
