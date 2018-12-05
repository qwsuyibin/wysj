package com.google.pay;

import com.google.pay.googlepay.IabResult;
import com.google.pay.googlepay.Purchase;

public interface GooglePayListener {
    void PayOver(Purchase pdata);
    void PayError(IabResult pdata);
}
