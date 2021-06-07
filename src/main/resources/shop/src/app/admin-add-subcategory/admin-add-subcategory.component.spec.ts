import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddSubcategory } from './admin-add-subcategory.component';

describe('AddSubcategoryDialogComponent', () => {
  let component: AdminAddSubcategory;
  let fixture: ComponentFixture<AdminAddSubcategory>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAddSubcategory ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAddSubcategory);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
