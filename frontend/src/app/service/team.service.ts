import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { Team } from '../model/team';
import { environment } from '../../environments/environment';
import { Player } from '../model/player';

@Injectable({ providedIn: 'root' })
export class TeamService {
  private teamUrl = 'teams';
  private playerUrl = 'teams/players';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(environment.apiUrl + this.teamUrl)
      .pipe(
        catchError(this.handleError<Team[]>('getTeams()', []))
      );
  }

  getPlayers(team: Team): Observable<Player[]> {
    const url = `${environment.apiUrl}${this.playerUrl}/${team.id}`
    return this.http.get<Player[]>(url)
      .pipe(
        catchError(this.handleError<Player[]>('getPlayers()', []))
      );
  }

  addTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(environment.apiUrl + this.teamUrl, team, this.httpOptions)
      .pipe(catchError(this.handleError<Team>('addTeam()')));
  }

  updateTeam(team: Team): Observable<any> {
    const url = `${environment.apiUrl}${this.teamUrl}/${team.id}`
    return this.http.put(url, team, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateTeam'))
    );
  }

  deleteTeam(team: Team | number): Observable<Team> {
    const id = typeof team === 'number' ? team : team.id;
    const url = `${environment.apiUrl}${this.teamUrl}/${id}`;
    return this.http.delete<Team>(url, this.httpOptions).pipe(
      catchError(this.handleError<Team>('deleteTeam'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
