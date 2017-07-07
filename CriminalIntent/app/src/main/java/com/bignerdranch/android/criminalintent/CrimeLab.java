package com.bignerdranch.android.criminalintent;


import android.content.Context;

import java.util.List;
import java.util.UUID;

/**
 * Created by Jesper on 2017-07-07.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){

    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime: mCrimes){
            if(crime.getID().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
