package library.application;

import library.common.ErrorDto;

public class BaseResponse {

  private final boolean success;
  private final ErrorDto error;

  /** For Success Response with no return data */
  protected BaseResponse() {
    this.success = true;
    this.error = null;
  }

  /** For Failure Response */
  public BaseResponse(ErrorDto error) {
    this.success = false;
    this.error = error;
  }

  public boolean isSuccess() {
    return success;
  }

  public ErrorDto getError() {
    return error;
  }
}
