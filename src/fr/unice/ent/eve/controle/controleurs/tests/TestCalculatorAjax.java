package fr.unice.ent.eve.controle.controleurs.tests;

import java.io.StringReader;
import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * A very simple calculator action that is designed to work with an ajax front
 * end. Handles 'add' and 'divide' events just like the non-ajax calculator.
 * Each event calculates the result, and then "streams" it back to the browser.
 * Implements the ValidationErrorHandler interface to intercept any validation
 * errors, convert them to an HTML message and stream the back to the browser
 * for display.
 * 
 * @author Tim Fennell
 */
public class TestCalculatorAjax implements ActionBean, ValidationErrorHandler {
  private ActionBeanContext context;
  @Validate (required = true)
  private double            numberOne;
  @Validate (required = true)
  private double            numberTwo;

  public TestCalculatorAjax () {
    super ();
  }

  /** Handles the 'add' event, adds the two numbers and returns the result. */
  @DefaultHandler
  public Resolution add () {
    final String result = String.valueOf (this.numberOne + this.numberTwo);
    return new StreamingResolution ("text", new StringReader (result)); //$NON-NLS-1$
  }

  /**
   * Handles the 'divide' event, divides number two by oneand returns the
   * result.
   */
  public Resolution divide () {
    final String result = String.valueOf (this.numberOne / this.numberTwo);
    return new StreamingResolution ("text", new StringReader (result)); //$NON-NLS-1$
  }

  @Override
  public ActionBeanContext getContext () {
    return this.context;
  }

  // Standard getter and setter methods
  public double getNumberOne () {
    return this.numberOne;
  }

  public double getNumberTwo () {
    return this.numberTwo;
  }

  /** Converts errors to HTML and streams them back to the browser. */
  @Override
  public Resolution handleValidationErrors (final ValidationErrors errors)
      throws Exception {
    final StringBuilder message = new StringBuilder ();

    for (final List<ValidationError> fieldErrors : errors.values ()) {
      for (final ValidationError error : fieldErrors) {
        message.append ("<div style=\"color: firebrick;\">"); //$NON-NLS-1$
        message.append (error.getMessage (this.getContext ().getLocale ()));
        message.append ("</div>"); //$NON-NLS-1$
      }
    }

    return new StreamingResolution (
        "text/html", new StringReader (message.toString ())); //$NON-NLS-1$
  }

  public Resolution multiply () {
    final String result = String.valueOf (this.numberOne * this.numberTwo);
    return new StreamingResolution ("text", new StringReader (result)); //$NON-NLS-1$
  }

  @Override
  public void setContext (final ActionBeanContext c) {
    this.context = c;
  }

  public void setNumberOne (final double nO) {
    this.numberOne = nO;
  }

  public void setNumberTwo (final double nT) {
    this.numberTwo = nT;
  }
}
