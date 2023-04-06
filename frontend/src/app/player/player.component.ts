import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../service/player.service';
import { Player } from '../model/player';
import { TeamService } from '../service/team.service';
import { Team } from '../model/team';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {
  players!: Player[];
  teams!: Team[];
  name!: string;
  surname!: string;
  age!: number;
  careerStart!: string;
  teamId!: number;

  constructor(private playerService: PlayerService,
              private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.getPlayers();
    this.getTeams();
  }

  getPlayers(): void {
    this.playerService.getPlayers()
      .subscribe(players => this.players = players);
  }

  getTeams(): void {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams);
  }

  getTeam(id: number): Team {
    let team: any = this.teams.find((team: Team) => team.id == id);
    if (!team) {
      return ({
        id: 0,
        name: '',
        players: [],
        funds: 0,
        city: '',
        country: '' })
    }
    return team
  }

  addPlayer(): void {
    this.playerService.addPlayer({name: this.name,
      surname: this.surname,
      age: this.age,
      careerStart: this.careerStart,
      teamId: this.teamId
    } as Player)
      .subscribe(player => {this.players.push(player)})
    this.name = '';
    this.surname = '';
    this.age = 0;
    this.careerStart = '';
  }

  updatePlayer(player: Player): void {
    this.playerService.updatePlayer(player).subscribe();
  }

  deletePlayer(player: Player): void {
    this.players = this.players.filter(t => t !== player);
    this.playerService.deletePlayer(player).subscribe();
  }

  transferPlayer(player: Player, id: number): void {
    this.playerService.transferPlayer(player, id).subscribe();
  }
}
