import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-input-increment',
  templateUrl: './input-increment.component.html',
  styleUrls: ['./input-increment.component.scss']
})
export class InputIncrementComponent implements OnInit {

  @Input() public count = 1;
  @Output() public incrementChange: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  public changeInput($event: Event): void {
    const input = $event.target as HTMLInputElement;
    this.count = Number(input.value);
    this.incrementChange.emit(this.count);
  }

  public increment(): void {
    this.count ++;
    this.incrementChange.emit(this.count);
  }

  public decrement(): void {
    if (this.count > 0) {
      this.count--;
      this.incrementChange.emit(this.count);
    }
  }

}
