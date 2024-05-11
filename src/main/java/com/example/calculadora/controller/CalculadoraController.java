package com.example.calculadora.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class CalculadoraController {

    @FXML
    private Button btn0, btn00, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnDelete, btnC, btnDivide, btnMultiply, btnSubtract, btnAdd, btnEqual, btnMode;

    @FXML
    private ImageView ivC, iv0, iv00, iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, ivDivide, ivPoint, ivSubtract, ivAdd, ivEqual, ivDelete, ivMultiply, ivMode, ivFondo;

    @FXML
    private TextField txfOperation, txfResult;

    @FXML
    private Label labelOperationSing, labelNumberNegative;

    private String screenNumber = "";
    private  double oldNumber = 0;
    private  double newNumber;
    private  double result = 0;
    private  String oldSymbol = "";
    private  String numberNegative = "";
    private  boolean firstNumber = true;
    private  boolean ModeDark = true;

    /*This variable is for the handle of removing content from the calculator,
     1 = deletes the text of the operation and
      2 = deletes result*/
    private int counterC = 0;
    private int counterContinueOperation = 0;

    public CalculadoraController() {
    }

    public void mouseActionButtonsNumbers(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        counterC = 0;
        if (sourceButton == btn0) {
            screenNumber += "0";
        } else if (sourceButton == btn1) {
            screenNumber += "1";
        } else if (sourceButton == btn2) {
            screenNumber += "2";
        } else if (sourceButton == btn3) {
            screenNumber += "3";
        } else if (sourceButton == btn4) {
            screenNumber += "4";
        } else if (sourceButton == btn5) {
            screenNumber += "5";
        } else if (sourceButton == btn6) {
            screenNumber += "6";
        } else if (sourceButton == btn7) {
            screenNumber += "7";
        } else if (sourceButton == btn8) {
            screenNumber += "8";
        } else if (sourceButton == btn9) {
            screenNumber += "9";
        } else if (sourceButton == btn00) {
            screenNumber += "00";
        } else if (sourceButton == btnPoint) {
            String screenNumber = txfOperation.getText();
            if (screenNumber.contains(".")) {
                return;
            }
            this.screenNumber += ".";
        }

        txfOperation.setText(screenNumber);

        /*Print screen number
        System.out.println("Numero enn pantalla " + screenNumber);*/
    }
    public void mouseClickedButtonsOperations(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();

        if (oldSymbol.isEmpty() && numberNegative.isEmpty() || oldSymbol.isEmpty() && numberNegative.equals("-")
                || !screenNumber.isEmpty()) {

            if (sourceButton == btnDivide) {
                if (oldSymbol.equals("/")) {
                    divide();
                } else if (oldSymbol.equals("+")) {
                    add();
                } else if (oldSymbol.equals("-")) {
                    subtract();
                } else if (oldSymbol.equals("*")) {
                    multiply();
                } else {
                    divide();
                }
                oldSymbol = "/";
                screenNumber = "";
                numberNegative = "";

            } else if (sourceButton == btnSubtract) {
                if (oldSymbol.equals("-")) {
                    subtract();
                } else if (oldSymbol.equals("+")) {
                    add();
                } else if (oldSymbol.equals("/")) {
                    divide();
                } else if (oldSymbol.equals("*")) {
                    multiply();
                }
                if (screenNumber.isEmpty()&&result==0) {
                    numberNegative = "-";
                    labelNumberNegative.setText(numberNegative);

                } else {
                    subtract();
                    oldSymbol = "-";
                    screenNumber = "";
                    labelNumberNegative.setText("");
                }


            } else if (sourceButton == btnMultiply) {
                if (oldSymbol.equals("*")) {
                    multiply();
                } else if (oldSymbol.equals("+")) {
                    add();
                } else if (oldSymbol.equals("-")) {
                    subtract();
                } else if (oldSymbol.equals("/")) {
                    divide();
                } else {
                    multiply();
                }
                oldSymbol = "*";
                screenNumber = "";
                numberNegative = "";

            } else if (sourceButton == btnAdd) {
                if (oldSymbol.equals("/")) {
                    divide();
                } else if (oldSymbol.equals("+")) {
                    add();
                } else if (oldSymbol.equals("-")) {
                    subtract();
                } else if (oldSymbol.equals("*")) {
                    multiply();
                } else {
                    add();
                }
                oldSymbol = "+";
                screenNumber = "";
                numberNegative = "";


            }
        }
        if (sourceButton == btnEqual) {
            newNumber = Double.parseDouble(screenNumber);
            if (!screenNumber.isEmpty()&&oldSymbol.isEmpty()){
                if (numberNegative.equals("-")) {
                    oldNumber -= newNumber;
                } else {
                    oldNumber += newNumber;
                }
                result=oldNumber;
            }else {
                if (oldSymbol.equals("-")) subtract();
                if (oldSymbol.equals("+")) add();
                if (oldSymbol.equals("*")) multiply();
                if (oldSymbol.equals("/")) divide();
            }


            oldSymbol = "";
            screenNumber = "";
            numberNegative = "";
            counterContinueOperation = 2;
            txfResult.setText(String.valueOf(result));
            labelOperationSing.setText(oldSymbol);

        }

        /* print properties calculator
        System.out.println("Ultimosigno " + oldSymbol);
        System.out.println("numero negativo " + numberNegative);
        System.out.println("old number " + oldNumber);
        System.out.println("new number " + oldNumber);
        System.out.println("resultado " + result);
        System.out.println("contador " + contContinueOperation);
         */
        txfOperation.setText(screenNumber);
        labelNumberNegative.setText(numberNegative);
        labelOperationSing.setText(oldSymbol);


    }

    public void mouseClickedButtonsFunctions(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnDelete) {
            String screenNumber = txfOperation.getText();
            if (!screenNumber.isEmpty()) {
                this.screenNumber = screenNumber.substring(0, screenNumber.length() - 1);
                txfOperation.setText(this.screenNumber);
            } else {
                //delete characters if it have some conditions
                if (!numberNegative.isEmpty() && !oldSymbol.isEmpty()) {
                    this.screenNumber = "";
                    numberNegative = "";

                } else if (!oldSymbol.isEmpty()) {
                    oldSymbol = "";
                    labelOperationSing.setText(oldSymbol);

                }
            }
        } else if (sourceButton == btnC) {
            if (!screenNumber.isEmpty() && counterC == 1) {
                labelOperationSing.setText("");
                firstNumber = true;
                counterContinueOperation = 0;

            } else if (screenNumber.isEmpty() && oldSymbol.isEmpty() || counterC == 2) {
                txfResult.setText(screenNumber);
                counterC = 0;
                counterContinueOperation = 0;
            }

            screenNumber = "";
            numberNegative = "";
            oldSymbol="";
            oldNumber = 0;
            result = 0;
            counterC++;
            labelNumberNegative.setText(numberNegative);
            txfOperation.setText(screenNumber);
            labelOperationSing.setText(oldSymbol);
        }
    }



    private void subtract() {
        try {
            newNumber = Double.parseDouble(screenNumber);
            counterContinueOperation++;
            if (firstNumber) {
                if (counterContinueOperation == 1) {
                    if (numberNegative.equals("-")) {
                        oldNumber -= newNumber;
                    } else {
                        oldNumber += newNumber;
                    }
                    result=oldNumber;
                    firstNumber = false;
                } else if (counterContinueOperation >= 2) {
                    result -= newNumber;
                }

            } else {
                oldNumber -= newNumber;
                result = oldNumber;
                firstNumber = true;
            }

            txfResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Por favor, ingresa números válidos.");
        }

    }

    private void multiply() {
        try {
            newNumber = Double.parseDouble(screenNumber);
            counterContinueOperation++;
            if (firstNumber) {
                if (counterContinueOperation == 1) {
                    if (numberNegative.equals("-")) {
                        oldNumber -= newNumber;
                    } else {
                        oldNumber += newNumber;
                    }
                    result=oldNumber;
                    firstNumber = false;
                } else if (counterContinueOperation >= 2) {
                    result *= newNumber;
                }

            } else {
                oldNumber *= newNumber;
                result = oldNumber;
                firstNumber = true;
            }

            txfResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Por favor, ingresa números válidos.");
        }

    }


    private void divide() {
        try {
            newNumber = Double.parseDouble(screenNumber);
            counterContinueOperation++;
            if (firstNumber) {
                if (counterContinueOperation == 1) {
                    if (numberNegative.equals("-")) {
                        oldNumber -= newNumber;
                    } else {
                        oldNumber += newNumber;
                    }
                    result=oldNumber;
                    firstNumber = false;
                } else if (counterContinueOperation >= 2) {
                    result /= newNumber;
                }
            } else {
                oldNumber /= newNumber;
                result = oldNumber;
                firstNumber = true;
            }

            txfResult.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Por favor, ingresa números válidos.");
        }

    }

    private void add() {
        try {
            newNumber = Double.parseDouble(screenNumber);
            counterContinueOperation++;
            if (firstNumber) {
                if (counterContinueOperation == 1) {
                    if (numberNegative.equals("-")) {
                        oldNumber -= newNumber;
                    } else {
                        oldNumber += newNumber;
                    }
                    result=oldNumber;
                    firstNumber = false;
                } else if (counterContinueOperation >= 2) {
                    result += newNumber;
                }

            } else {
                oldNumber += newNumber;
                result = oldNumber;
                firstNumber = true;
            }

            txfResult.setText(String.valueOf(result));
        } catch (Exception e) {
            System.out.println("Error: Entrada inválida. Por favor, ingresa números válidos.");
        }
    }


    public void mouseActionModeTheme(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        Image imageButtons;
        Image imageBackground;

        if (sourceButton == btnMode) {
            if (ModeDark) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnModeSun.png")));
                ivMode.setImage(imageButtons);
                imageBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/FondoCalcu-creartheme.png")));
                ivFondo.setImage(imageBackground);

                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn0-clearTheme.png")));
                iv0.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn00clearTheme.png")));
                iv00.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn1-clearTheme.png")));
                iv1.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn2-clearTheme.png")));
                iv2.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn3-clearTheme.png")));
                iv3.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn4-clearTheme.png")));
                iv4.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn5-clearTheme.png")));
                iv5.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn6-clearTheme.png")));
                iv6.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn7-clearTheme.png")));
                iv7.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn8-clearTheme.png")));
                iv8.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn9-clearTheme9.png")));
                iv9.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnx-clearTheme.png")));
                ivMultiply.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn+clearTheme.png")));
                ivAdd.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-clearTheme.png")));
                ivSubtract.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDivide-clearTheme.png")));
                ivDivide.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDelete-clearTheme.png")));
                ivDelete.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnC-clearTheme.png")));
                ivC.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn=-clearTheme.png")));
                ivEqual.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-.clearTheme.png")));
                ivPoint.setImage(imageButtons);


                ModeDark = false;
            } else {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnModenight.png")));
                ivMode.setImage(imageButtons);
                imageBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/FondoCalcu.png")));
                ivFondo.setImage(imageBackground);

                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn0.png")));
                iv0.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn00.png")));
                iv00.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn1.png")));
                iv1.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn2.png")));
                iv2.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn3.png")));
                iv3.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn4.png")));
                iv4.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn5.png")));
                iv5.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn6.png")));
                iv6.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn7.png")));
                iv7.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn8.png")));
                iv8.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn9.png")));
                iv9.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnx.png")));
                ivMultiply.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn+.png")));
                ivAdd.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-.png")));
                ivSubtract.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDivide.png")));
                ivDivide.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDelete.png")));
                ivDelete.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnC.png")));
                ivC.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn=.png")));
                ivEqual.setImage(imageButtons);
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn..png")));
                ivPoint.setImage(imageButtons);
                ModeDark = true;
            }
        }

    }

    public void mouseEnterButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        Image imageButtons;
        if (ModeDark) {
            if (sourceButton == btn0) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn0-Selected.png")));
                iv0.setImage(imageButtons);
            } else if (sourceButton == btn00) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn00Selected.png")));
                iv00.setImage(imageButtons);
            } else if (sourceButton == btn1) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn1Selected.png")));
                iv1.setImage(imageButtons);
            } else if (sourceButton == btn2) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn2Selected.png")));
                iv2.setImage(imageButtons);
            } else if (sourceButton == btn3) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn3Selected.png")));
                iv3.setImage(imageButtons);
            } else if (sourceButton == btn4) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn4Selected.png")));
                iv4.setImage(imageButtons);
            } else if (sourceButton == btn5) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn5Selected.png")));
                iv5.setImage(imageButtons);
            } else if (sourceButton == btn6) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn6Selected.png")));
                iv6.setImage(imageButtons);
            } else if (sourceButton == btn7) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn7Selected.png")));
                iv7.setImage(imageButtons);
            } else if (sourceButton == btn8) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn8Selected.png")));
                iv8.setImage(imageButtons);
            } else if (sourceButton == btn9) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn9Selected.png")));
                iv9.setImage(imageButtons);
            } else if (sourceButton == btnMultiply) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnx-selected.png")));
                ivMultiply.setImage(imageButtons);
            } else if (sourceButton == btnAdd) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn+Selected.png")));
                ivAdd.setImage(imageButtons);
            } else if (sourceButton == btnSubtract) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-Selected.png")));
                ivSubtract.setImage(imageButtons);
            } else if (sourceButton == btnDivide) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDivide-Selected.png")));
                ivDivide.setImage(imageButtons);
            } else if (sourceButton == btnDelete) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDelete-Selected.png")));
                ivDelete.setImage(imageButtons);
            } else if (sourceButton == btnC) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnC-Selected.png")));
                ivC.setImage(imageButtons);
            } else if (sourceButton == btnEqual) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn=-Selected.png")));
                ivEqual.setImage(imageButtons);
            } else if (sourceButton == btnPoint) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn.Selectedt.png")));
                ivPoint.setImage(imageButtons);
            }
        } else {
            if (sourceButton == btn0) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn0-clearThemeSelected.png")));
                iv0.setImage(imageButtons);
            } else if (sourceButton == btn00) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn00clearThemeSelected.png")));
                iv00.setImage(imageButtons);
            } else if (sourceButton == btn1) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn1-clearThemeSelected.png")));
                iv1.setImage(imageButtons);
            } else if (sourceButton == btn2) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn2-clearThemeSelected.png")));
                iv2.setImage(imageButtons);
            } else if (sourceButton == btn3) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn3-clearThemeSelected.png")));
                iv3.setImage(imageButtons);
            } else if (sourceButton == btn4) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn4-clearThemeSelected.png")));
                iv4.setImage(imageButtons);
            } else if (sourceButton == btn5) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn5-clearThemeSelected.png")));
                iv5.setImage(imageButtons);
            } else if (sourceButton == btn6) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn6-clearThemeSelected.png")));
                iv6.setImage(imageButtons);
            } else if (sourceButton == btn7) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn7-clearThemeSelected.png")));
                iv7.setImage(imageButtons);
            } else if (sourceButton == btn8) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn8-clearThemeSelected.png")));
                iv8.setImage(imageButtons);
            } else if (sourceButton == btn9) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn9-clearThemeSelected.png")));
                iv9.setImage(imageButtons);
            } else if (sourceButton == btnMultiply) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnx-clearThemeSelected.png")));
                ivMultiply.setImage(imageButtons);
            } else if (sourceButton == btnAdd) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn+clearThemeSelected.png")));
                ivAdd.setImage(imageButtons);
            } else if (sourceButton == btnSubtract) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-clearThemeSelected.png")));
                ivSubtract.setImage(imageButtons);
            } else if (sourceButton == btnDivide) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDivide-clearThemeSelected.png")));
                ivDivide.setImage(imageButtons);
            } else if (sourceButton == btnDelete) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDelete-clearThemeSelected.png")));
                ivDelete.setImage(imageButtons);
            } else if (sourceButton == btnC) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnC-clearThemeSelected.png")));
                ivC.setImage(imageButtons);
            } else if (sourceButton == btnEqual) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn=-clearThemeSelected.png")));
                ivEqual.setImage(imageButtons);
            } else if (sourceButton == btnPoint) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-.clearThemeSelected.png")));
                ivPoint.setImage(imageButtons);
            }
        }

    }

    public void mouseExitButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        Image imageButtons;
        if (ModeDark) {
            if (sourceButton == btn0) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn0.png")));
                iv0.setImage(imageButtons);
            } else if (sourceButton == btn00) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn00.png")));
                iv00.setImage(imageButtons);
            } else if (sourceButton == btn1) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn1.png")));
                iv1.setImage(imageButtons);
            } else if (sourceButton == btn2) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn2.png")));
                iv2.setImage(imageButtons);
            } else if (sourceButton == btn3) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn3.png")));
                iv3.setImage(imageButtons);
            } else if (sourceButton == btn4) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn4.png")));
                iv4.setImage(imageButtons);
            } else if (sourceButton == btn5) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn5.png")));
                iv5.setImage(imageButtons);
            } else if (sourceButton == btn6) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn6.png")));
                iv6.setImage(imageButtons);
            } else if (sourceButton == btn7) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn7.png")));
                iv7.setImage(imageButtons);
            } else if (sourceButton == btn8) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn8.png")));
                iv8.setImage(imageButtons);
            } else if (sourceButton == btn9) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn9.png")));
                iv9.setImage(imageButtons);
            } else if (sourceButton == btnMultiply) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnx.png")));
                ivMultiply.setImage(imageButtons);
            } else if (sourceButton == btnAdd) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn+.png")));
                ivAdd.setImage(imageButtons);
            } else if (sourceButton == btnSubtract) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-.png")));
                ivSubtract.setImage(imageButtons);
            } else if (sourceButton == btnDivide) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDivide.png")));
                ivDivide.setImage(imageButtons);
            } else if (sourceButton == btnDelete) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDelete.png")));
                ivDelete.setImage(imageButtons);
            } else if (sourceButton == btnC) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnC.png")));
                ivC.setImage(imageButtons);
            } else if (sourceButton == btnEqual) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn=.png")));
                ivEqual.setImage(imageButtons);
            } else if (sourceButton == btnPoint) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn..png")));
                ivPoint.setImage(imageButtons);
            }
        } else {
            if (sourceButton == btn0) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn0-clearTheme.png")));
                iv0.setImage(imageButtons);
            } else if (sourceButton == btn00) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn00clearTheme.png")));
                iv00.setImage(imageButtons);
            } else if (sourceButton == btn1) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn1-clearTheme.png")));
                iv1.setImage(imageButtons);
            } else if (sourceButton == btn2) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn2-clearTheme.png")));
                iv2.setImage(imageButtons);
            } else if (sourceButton == btn3) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn3-clearTheme.png")));
                iv3.setImage(imageButtons);
            } else if (sourceButton == btn4) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn4-clearTheme.png")));
                iv4.setImage(imageButtons);
            } else if (sourceButton == btn5) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn5-clearTheme.png")));
                iv5.setImage(imageButtons);
            } else if (sourceButton == btn6) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn6-clearTheme.png")));
                iv6.setImage(imageButtons);
            } else if (sourceButton == btn7) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn7-clearTheme.png")));
                iv7.setImage(imageButtons);
            } else if (sourceButton == btn8) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn8-clearTheme.png")));
                iv8.setImage(imageButtons);
            } else if (sourceButton == btn9) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn9-clearTheme9.png")));
                iv9.setImage(imageButtons);
            } else if (sourceButton == btnMultiply) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnx-clearTheme.png")));
                ivMultiply.setImage(imageButtons);
            } else if (sourceButton == btnAdd) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn+clearTheme.png")));
                ivAdd.setImage(imageButtons);
            } else if (sourceButton == btnSubtract) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-clearTheme.png")));
                ivSubtract.setImage(imageButtons);
            } else if (sourceButton == btnDivide) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDivide-clearTheme.png")));
                ivDivide.setImage(imageButtons);
            } else if (sourceButton == btnDelete) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnDelete-clearTheme.png")));
                ivDelete.setImage(imageButtons);
            } else if (sourceButton == btnC) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btnC-clearTheme.png")));
                ivC.setImage(imageButtons);
            } else if (sourceButton == btnEqual) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn=-clearTheme.png")));
                ivEqual.setImage(imageButtons);
            } else if (sourceButton == btnPoint) {
                imageButtons = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/btn-.clearTheme.png")));
                ivPoint.setImage(imageButtons);
            }
        }
    }


}
