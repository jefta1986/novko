import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoEntriesListComponent } from './no-entries-list.component';

describe('NoEntriesListComponent', () => {
  let component: NoEntriesListComponent;
  let fixture: ComponentFixture<NoEntriesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoEntriesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoEntriesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
