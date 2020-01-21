package lit_fits_client.views.themes;

import java.util.Objects;

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
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Theme other = (Theme) obj;
        return Objects.equals(this.themeCss, other.getThemeCss());
    }

    @Override
    public String toString() {
        return themeCss.substring(7, themeCss.length() - 4);
    }
}
