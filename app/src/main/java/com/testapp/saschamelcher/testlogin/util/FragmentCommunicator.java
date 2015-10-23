package com.testapp.saschamelcher.testlogin.util;

import com.testapp.saschamelcher.testlogin.model.Vocabs;

import java.util.List;

/**
 * Created by saschamelcher on 02/09/15.
 */

public interface FragmentCommunicator{
    public void passListToFragment(List<Vocabs> cardSet);
}
