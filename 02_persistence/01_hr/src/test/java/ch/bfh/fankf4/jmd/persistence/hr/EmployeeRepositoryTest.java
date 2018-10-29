package ch.bfh.fankf4.jmd.persistence.hr;

import ch.bfh.fankf4.jmd.persistence.hr.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

  @Autowired
  private HrApplicationRepository hrRepository;

  @Test
  public void save() {
    Employee employee = new Employee();
    employee.setId(15);
    hrRepository.saveAndFlush(employee);
  }

  @Test
  public void findById() {
    Optional<Employee> optionalEmployee = hrRepository.findById(1);
  }

  @Test
  public void testQuery() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setName("Peter");
    hrRepository.saveAndFlush(employee);
    employee = new Employee();
    employee.setId(2);
    employee.setName("Paul");
    hrRepository.saveAndFlush(employee);

    List<Employee> list = hrRepository.findAllByNameLike("Pau%");
    Assert.assertEquals(((List) list).size(), 1);
  }
}
