import {Team} from "./team";

export interface Player {
  id: number;
  name: string;
  surname: string;
  age: number;
  careerStart: string;
  price: number;
  teamId: number;
}
