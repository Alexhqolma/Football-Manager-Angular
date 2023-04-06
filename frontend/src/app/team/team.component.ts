import { Component, OnInit } from '@angular/core';
import { TeamService } from '../service/team.service';
import { Team } from '../model/team';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  teams: Team[] = [];
  name!: string;
  city!: string;
  country!: string;
  funds!: number;

  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams);
  }

  getPlayers(team: Team): void {
    this.teamService.getPlayers(team)
      .subscribe(players => team.players = players);
  }

  addTeam(): void {
    this.teamService.addTeam({name: this.name,
      city: this.city,
      country: this.country,
      funds: this.funds,
    } as Team)
      .subscribe(team => {this.teams.push(team)})
    this.name = '';
    this.city = '';
    this.country = '';
    this.funds = 0;
  }

  updateTeam(team: Team): void {
    this.teamService.updateTeam(team).subscribe();
  }

  deleteTeam(team: Team): void {
    this.teams = this.teams.filter(t => t !== team);
    this.teamService.deleteTeam(team).subscribe();
  }
}
