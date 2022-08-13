package venkat.demoproject.Controller;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import venkat.demoproject.model.Team;
import venkat.demoproject.Repository.*;

@RestController
public class TeamController {

    private TeamRepo TeamRepo;
    private MatchRepository MatchRepository;

    public TeamController(TeamRepo repo, MatchRepository MatchRepository) {
        this.TeamRepo = repo;
        this.MatchRepository = MatchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.TeamRepo.findByTeamName(teamName);
        org.springframework.data.domain.Pageable pageable = PageRequest.of(0, 4);

        team.setMatches(this.MatchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable));
        return team;
    }

}