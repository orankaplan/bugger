package com.vmware.bugger.service;

import com.vmware.bugger.model.Culprit;
import com.vmware.bugger.model.ErrorStack;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abadyan on 23/12/15.
 */
@Service
public class GitBlamerService {
    public List<Culprit> blame(ErrorStack errorStack) {
        return null;
    }
}
