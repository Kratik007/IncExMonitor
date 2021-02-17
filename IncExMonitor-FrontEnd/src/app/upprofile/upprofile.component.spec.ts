import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpprofileComponent } from './upprofile.component';

describe('UpprofileComponent', () => {
  let component: UpprofileComponent;
  let fixture: ComponentFixture<UpprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
