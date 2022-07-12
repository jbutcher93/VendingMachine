public class Program {
    public static void main(String[] args) {

        int nextMenu;
        VendingMachine vm = new VendingMachine();
        vm.setVendingMachineItems(vm.stockVendingMachine());
        UI ui = new UI();

        ui.setCurrentMenuState(UI.menu_state.MAIN_MENU);

            while (ui.getCurrentMenuState() != UI.menu_state.EXIT) {
                try {
                if (ui.getCurrentMenuState() == UI.menu_state.MAIN_MENU) {
                    nextMenu = ui.displayMainMenu();
                    if (nextMenu == 1) {
                        ui.setCurrentMenuState(UI.menu_state.DISPLAY_ITEMS);
                    } else if (nextMenu == 2) {
                        ui.setCurrentMenuState(UI.menu_state.PURCHASE_MENU);
                    } else if (nextMenu == 3) {
                        ui.setCurrentMenuState(UI.menu_state.EXIT);
                    } else if (nextMenu == 4) {
                        vm.writeSalesReport();
                    } else {
                        System.out.println("That wasn't an option");
                    }
                } // end of MAIN_MENU

                else if (ui.getCurrentMenuState() == UI.menu_state.DISPLAY_ITEMS) {
                    ui.displayItemsMenu(vm);
                }  // end of DISPLAY_ITEMS

                else if (ui.getCurrentMenuState() == UI.menu_state.PURCHASE_MENU) {
                    nextMenu = ui.purchaseMenu(vm);
                    if (nextMenu == 1) {
                        ui.setCurrentMenuState(UI.menu_state.FEED_MONEY_MENU);
                    } else if (nextMenu == 2) {
                        ui.setCurrentMenuState(UI.menu_state.SELECT_PRODUCT_MENU);
                    } else if (nextMenu == 3) {
                        ui.setCurrentMenuState(UI.menu_state.FINISH_TRANSACTION_MENU);
                    } else {
                        System.out.println("That wasn't an option");
                    }
                }  // end of PURCHASE_MENU

                else if (ui.getCurrentMenuState() == UI.menu_state.FEED_MONEY_MENU) {
                    ui.feedMoneyMenu(vm);
                    ui.setCurrentMenuState(UI.menu_state.PURCHASE_MENU);
                } // end of FEED_MONEY_MENU

                else if (ui.getCurrentMenuState() == UI.menu_state.SELECT_PRODUCT_MENU) {
                    String code = ui.selectProductMenu(vm);
                    ui.setCurrentMenuState(vm.updateInventory(code));
                } // end of SELECT_PRODUCT_MENU

                else if (ui.getCurrentMenuState() == UI.menu_state.FINISH_TRANSACTION_MENU) {
                    vm.resetBalance();
                    ui.setCurrentMenuState(UI.menu_state.MAIN_MENU);
                } // end of FINISH_TRANSACTION_MENU

                } catch (NumberFormatException nfe) {
                    System.out.println("You'll need to enter a number.");
                } catch (Exception e) {
                    System.out.println("We caught an exception.");
                }
                } // end of while loop
        System.out.println("End of program");
        
    } // main

} // class
