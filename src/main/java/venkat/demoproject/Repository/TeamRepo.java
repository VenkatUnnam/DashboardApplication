package venkat.demoproject.Repository;

import org.springframework.data.repository.CrudRepository;

import venkat.demoproject.model.Team;

public interface TeamRepo extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);

}
