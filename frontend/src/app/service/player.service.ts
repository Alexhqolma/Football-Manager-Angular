import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { Player } from '../model/player';
import {Team} from "../model/team";

@Injectable({ providedIn: 'root' })
export class PlayerService {
  private playerUrl = 'players';
  private transferUrl = 'players/transfer';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(environment.apiUrl + this.playerUrl)
      .pipe(
        catchError(this.handleError<Player[]>('getPlayer()', []))
      );
  }

  addPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>(environment.apiUrl + this.playerUrl, player, this.httpOptions)
      .pipe(catchError(this.handleError<Player>('addPlayer()')));
  }

  updatePlayer(player: Player): Observable<any> {
    const url = `${environment.apiUrl}${this.playerUrl}/${player.id}`
    return this.http.put(url, player, this.httpOptions).pipe(
      catchError(this.handleError<any>('updatePlayer'))
    );
  }

  deletePlayer(player: Player | number): Observable<Player> {
    const id = typeof player === 'number' ? player : player.id;
    const url = `${environment.apiUrl}${this.playerUrl}/${id}`;
    return this.http.delete<Player>(url, this.httpOptions).pipe(
      catchError(this.handleError<Player>('deletePlayer'))
    );
  }

  transferPlayer(player: Player, teamToId: number): Observable<any> {
    const url = `${environment.apiUrl}${this.transferUrl}/${teamToId}`
    return this.http.put(url, player, this.httpOptions).pipe(
      catchError(this.handleError<any>('transferPlayer'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
