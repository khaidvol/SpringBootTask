package com.epam.jgmp.sbt;

import com.epam.jgmp.sbt.controller.CarServiceControllerTest;
import com.epam.jgmp.sbt.integration.ControllerIntegrationTest;
import com.epam.jgmp.sbt.repository.CarRepositoryTest;
import com.epam.jgmp.sbt.service.implementation.CarServiceImplTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        CarRepositoryTest.class,
        CarServiceImplTest.class,
        CarServiceControllerTest.class,
        ControllerIntegrationTest.class,

})
public class TestAll {
}
