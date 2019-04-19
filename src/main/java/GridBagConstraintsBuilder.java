import java.awt.*;

/**
 * A builder class for making creating GridBagConstraints
 * a little easier and more readable
 */
class GridBagConstraintsBuilder extends GridBagConstraints {

    private GridBagConstraintsBuilder(Builder builder ) {

        this.gridx = builder.gridx;
        this.gridy = builder.gridy;

        this.gridheight = builder.gridheight;
        this.gridwidth = builder.gridwidth;

        this.anchor = builder.anchor;

        this.insets = builder.insets;
    }

    static class Builder extends GridBagConstraints {

        static Builder newInstance() {

            return new Builder();
        }

        private Builder() { }

        Builder setDimensions(int height, int width) {

            this.gridheight = height;
            this.gridwidth = width;

            return this;
        }

        Builder setGrid(int x, int y) {

            this.gridx = x;
            this.gridy = y;

            return this;
        }

        Builder setAnchor(int anchor) {

            this.anchor = anchor;

            return this;
        }

        Builder setInsets(Insets insets) {

            this.insets = insets;

            return this;
        }

        GridBagConstraintsBuilder build() {

            return new GridBagConstraintsBuilder(this);
        }
    }
}