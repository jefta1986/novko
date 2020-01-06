import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcategorydialogComponent } from './addcategorydialog.component';

describe('AddcategorydialogComponent', () => {
  let component: AddcategorydialogComponent;
  let fixture: ComponentFixture<AddcategorydialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddcategorydialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddcategorydialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
