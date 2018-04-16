import javax.print.attribute.standard.NumberUp;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * An add controller of a product inventory application.
 *
 * <p>CS18000 -- Spring 2018 -- Complex GUIs -- Homework</p>
 */
public final class AddController {
    /**
     * The inventory model of this add controller.
     */
    private InventoryModel inventoryModel;

    /**
     * The add view of this add controller.
     */
    private AddView addView;

    /**
     * Constructs a newly allocated {@code AddController} object with the specified inventory model and add view.
     *
     * @param inventoryModel the inventory model of this add controller
     * @param addView the add view of this add controller
     * @throws IllegalArgumentException if the {@code inventoryModel} argument or {@code addView} argument is
     * {@code null}
     */
    public AddController(InventoryModel inventoryModel, AddView addView) throws IllegalArgumentException {
        if (inventoryModel == null) {
            throw new IllegalArgumentException("inventoryModel argument is null");
        } else if (addView == null) {
            throw new IllegalArgumentException("addView argument is null");
        } else {
            this.inventoryModel = inventoryModel;
            this.addView = addView;

            this.addView.getAddButton().addActionListener(e -> this.getAddButtonSemantics());

            this.addView.getClearButton().addActionListener(e -> this.getClearButtonSemantics());
        } //end if
    } //AddController

    /**
     * Gets the semantics of an add view's add button.
     */
    private void getAddButtonSemantics() {
        //TODO implement method
        final boolean[] validIn = {true};

                    if (inventoryModel.searchBySku(addView.getSkuTextField().getText()).isPresent()) {
                        JOptionPane.showMessageDialog(null, "This Product SKU is already in use!");
                        validIn[0] = false;
                    }

                    try {
                        if (!(Double.parseDouble(addView.getWholesalePriceTextField().getText()) > 0)) {
                            JOptionPane.showMessageDialog(null, "Wholesale price must be above 0");
                            validIn[0] = false;
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Wholesale price must be a real number");
                        validIn[0] = false;
                    }

                    try {
                        if (!(Integer.parseInt(addView.getQuantityTextField().getText()) > 0)) {
                            JOptionPane.showMessageDialog(null, "Quantity must be above 0");
                            validIn[0] = false;
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Quantity must be an integer");
                        validIn[0] = false;
                    }

                    try {
                        if (!(Double.parseDouble(addView.getRetailPriceTextField().getText()) > 0)) {
                            JOptionPane.showMessageDialog(null, "Retail price must be above 0");
                            validIn[0] = false;
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Retail price must be a real number");
                        validIn[0] = false;
                    }

                    if (validIn[0]) {
                        Product addProduct = new Product(addView.getSkuTextField().getText(), addView.getNameTextField().getText(), Double.parseDouble(addView.getWholesalePriceTextField().getText()), Double.parseDouble(addView.getRetailPriceTextField().getText()), Integer.parseInt(addView.getQuantityTextField().getText()));
                        inventoryModel.add(addProduct);
                        JOptionPane.showMessageDialog(null, "Product added to inventory");
                    }
                }
            });
        }
    } //getAddButtonSemantics

    /**
     * Gets the semantics of an add view's clear button.
     */
    private void getClearButtonSemantics() {
        //TODO implement method
        addView.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addView.getNameTextField().setText("");
                addView.getQuantityTextField().setText("");
                addView.getRetailPriceTextField().setText("");
                addView.getWholesalePriceTextField().setText("");
                addView.getSkuTextField().setText("");
                addView.getSkuTextField().requestFocus();

            }
        });
    } //getClearButtonSemantics

    /**
     * Gets the hash code of this add controller.
     *
     * @return the hash code of this add controller
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + (this.inventoryModel == null ? 0 : this.inventoryModel.hashCode());

        result = 19 * result + (this.addView == null ? 0 : this.addView.hashCode());

        return result;
    } //hashCode

    /**
     * Determines whether or not this add controller is equal to the specified object. {@code true} is returned if and
     * only if the specified object is an instance of {@code AddController}, and its field values are equal to this
     * add controller's.
     *
     * @param anObject the object to be compared
     * @return {@code true}, if this add controller is equal to the specified object, and {@code false} otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        return (anObject instanceof AddController)
                && (this.inventoryModel == null ? ((AddController) anObject).inventoryModel ==  null : this.inventoryModel.equals(((AddController) anObject).inventoryModel))
                && (this.addView == null ? ((AddController) anObject).addView ==  null : this.addView.equals(((AddController) anObject).addView));
    } //equals

    /**
     * Gets a {@code String} representation of this add controller.
     *
     * @return a {@code String} representation of this add controller
     */
    @Override
    public String toString() {
        return String.format("AddController[%s, %s]", this.inventoryModel, this.addView);
    } //toString
}